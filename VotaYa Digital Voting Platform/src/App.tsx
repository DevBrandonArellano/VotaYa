import React, { useState } from 'react';
import { Shield, Download, Mail, Check, FileText, User, Calendar, Hash, ShieldCheck } from 'lucide-react';
import { Button } from './components/Button';
import { Input } from './components/Input';
import { Card } from './components/Card';
import { Badge } from './components/Badge';
import { SecurityAlert } from './components/SecurityAlert';
import { TrustBlock } from './components/TrustBlock';

type Screen = 'login' | 'voting' | 'certificate';

interface VoteData {
  [key: string]: string;
}

export default function App() {
  const [currentScreen, setCurrentScreen] = useState<Screen>('login');
  const [cedula, setCedula] = useState('');
  const [cedulaError, setCedulaError] = useState('');
  const [votes, setVotes] = useState<VoteData>({});

  // Datos de ejemplo para las preguntas
  const questions = [
    {
      id: 'q1',
      title: '¿Está de acuerdo con la propuesta de reforma educativa?',
      options: [
        { id: 'q1-si', label: 'Sí, estoy de acuerdo' },
        { id: 'q1-no', label: 'No, no estoy de acuerdo' },
        { id: 'q1-blanco', label: 'Voto en blanco' },
      ]
    },
    {
      id: 'q2',
      title: '¿Aprueba la inversión en infraestructura vial?',
      options: [
        { id: 'q2-si', label: 'Sí, apruebo' },
        { id: 'q2-no', label: 'No, desapruebo' },
        { id: 'q2-blanco', label: 'Voto en blanco' },
      ]
    },
    {
      id: 'q3',
      title: '¿Está de acuerdo con el plan de desarrollo sostenible?',
      options: [
        { id: 'q3-si', label: 'Sí, de acuerdo' },
        { id: 'q3-no', label: 'No, en desacuerdo' },
        { id: 'q3-blanco', label: 'Voto en blanco' },
      ]
    }
  ];

  const handleLogin = () => {
    // Validación simple
    if (!cedula || cedula.length < 10) {
      setCedulaError('Por favor ingrese un número de cédula válido (10 dígitos)');
      return;
    }
    setCedulaError('');
    setCurrentScreen('voting');
  };

  const handleVote = (questionId: string, optionId: string) => {
    setVotes({ ...votes, [questionId]: optionId });
  };

  const handleSubmitVote = () => {
    // Verificar que todas las preguntas tengan respuesta
    const allAnswered = questions.every(q => votes[q.id]);
    if (!allAnswered) {
      alert('Por favor responda todas las preguntas antes de continuar.');
      return;
    }
    setCurrentScreen('certificate');
  };

  const handleDownloadCertificate = () => {
    alert('Descargando certificado PDF... (Funcionalidad simulada)');
  };

  const handleEmailCertificate = () => {
    alert('Enviando certificado por correo electrónico... (Funcionalidad simulada)');
  };

  const renderLogin = () => (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-blue-50 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        {/* Logo y título */}
        <div className="text-center mb-8">
          <div className="inline-flex items-center justify-center w-20 h-20 bg-[#3498DB] rounded-full mb-4 shadow-lg">
            <Shield className="w-10 h-10 text-white" />
          </div>
          <h1 className="text-gray-900 mb-2">VotaYa</h1>
          <p className="text-gray-600">Sistema Digital de Votación Segura</p>
        </div>

        {/* Formulario de autenticación */}
        <Card>
          <div className="space-y-6">
            <div>
              <h2 className="text-gray-900 mb-2">Autenticación Segura</h2>
              <p className="text-gray-600">Ingrese su información para acceder al sistema de votación</p>
            </div>

            <Input
              label="Número de Cédula"
              value={cedula}
              onChange={setCedula}
              error={cedulaError}
              placeholder="Ej: 1234567890"
              required
            />

            <Button
              variant="primary"
              onClick={handleLogin}
              fullWidth
              icon={<Shield className="w-5 h-5" />}
            >
              Autenticar con FirmaEc
            </Button>

            <TrustBlock message="Su identidad será verificada mediante firma electrónica FirmaEc. Este proceso es seguro y cumple con la normativa ecuatoriana." />
          </div>
        </Card>

        {/* Información adicional */}
        <div className="mt-6 text-center text-sm text-gray-500">
          <p>Sistema protegido con tecnología de firma electrónica</p>
          <p className="mt-1">© 2025 VotaYa - Todos los derechos reservados</p>
        </div>
      </div>
    </div>
  );

  const renderVoting = () => (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-blue-50">
      {/* Header fijo */}
      <div className="bg-white shadow-md sticky top-0 z-10">
        <div className="max-w-4xl mx-auto px-4 py-4">
          <div className="flex items-center justify-between mb-3">
            <div className="flex items-center gap-3">
              <div className="w-12 h-12 bg-[#3498DB] rounded-full flex items-center justify-center">
                <Shield className="w-6 h-6 text-white" />
              </div>
              <div>
                <h1 className="text-gray-900">VotaYa</h1>
                <p className="text-sm text-gray-500">Proceso Electoral 2025</p>
              </div>
            </div>
            <Badge status="active">
              <Check className="w-4 h-4" />
              Autenticado
            </Badge>
          </div>

          <SecurityAlert message="Su voto será cifrado y validado mediante firma electrónica FirmaEc, garantizando la unicidad y el no repudio." />
        </div>
      </div>

      {/* Contenido scrollable */}
      <div className="max-w-4xl mx-auto px-4 py-6 pb-32">
        <div className="mb-6">
          <h2 className="text-gray-900 mb-2">Emisión de Voto Único</h2>
          <p className="text-gray-600">Seleccione su respuesta para cada pregunta. Su voto es confidencial y seguro.</p>
        </div>

        <div className="space-y-6">
          {questions.map((question, index) => (
            <Card key={question.id}>
              <div className="mb-4">
                <div className="flex items-start gap-3">
                  <span className="flex-shrink-0 w-8 h-8 bg-[#3498DB] text-white rounded-full flex items-center justify-center">
                    {index + 1}
                  </span>
                  <div className="flex-1">
                    <h3 className="text-gray-900 mb-4">{question.title}</h3>
                    <div className="space-y-3">
                      {question.options.map((option) => (
                        <label
                          key={option.id}
                          className={`flex items-center gap-3 p-4 border-2 rounded-lg cursor-pointer transition-all ${
                            votes[question.id] === option.id
                              ? 'border-[#3498DB] bg-blue-50'
                              : 'border-gray-200 hover:border-[#3498DB] hover:bg-blue-50'
                          }`}
                        >
                          <input
                            type="radio"
                            name={question.id}
                            value={option.id}
                            checked={votes[question.id] === option.id}
                            onChange={() => handleVote(question.id, option.id)}
                            className="w-5 h-5 text-[#3498DB] focus:ring-[#3498DB]"
                          />
                          <span className="flex-1 text-gray-700">{option.label}</span>
                        </label>
                      ))}
                    </div>
                  </div>
                </div>
              </div>
            </Card>
          ))}
        </div>
      </div>

      {/* Botón fijo inferior */}
      <div className="fixed bottom-0 left-0 right-0 bg-white border-t-2 border-gray-200 shadow-lg">
        <div className="max-w-4xl mx-auto px-4 py-4">
          <Button
            variant="primary"
            onClick={handleSubmitVote}
            fullWidth
            icon={<ShieldCheck className="w-5 h-5" />}
          >
            Comenzar Votación / Validar con FirmaEC
          </Button>
          <p className="text-center text-sm text-gray-500 mt-2">
            Al continuar, su voto será registrado de forma segura e irrevocable
          </p>
        </div>
      </div>
    </div>
  );

  const renderCertificate = () => {
    const currentDate = new Date().toLocaleDateString('es-EC', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });

    return (
      <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-blue-50 py-8 px-4">
        <div className="max-w-4xl mx-auto">
          {/* Header de éxito */}
          <div className="text-center mb-8">
            <div className="inline-flex items-center justify-center w-20 h-20 bg-green-500 rounded-full mb-4 shadow-lg">
              <Check className="w-10 h-10 text-white" />
            </div>
            <h1 className="text-gray-900 mb-2">Voto Registrado Exitosamente</h1>
            <p className="text-gray-600">Su participación ha sido validada mediante firma electrónica</p>
          </div>

          {/* Certificado digital */}
          <Card variant="bordered" className="mb-6">
            <div className="space-y-6">
              {/* Header del certificado */}
              <div className="text-center border-b-2 border-gray-200 pb-4">
                <div className="flex items-center justify-center gap-3 mb-2">
                  <Shield className="w-8 h-8 text-[#3498DB]" />
                  <h2 className="text-gray-900">Certificado Digital de Participación Electoral</h2>
                </div>
                <Badge status="validated">
                  <Check className="w-4 h-4" />
                  Firma Electrónica Validada
                </Badge>
              </div>

              {/* Información del votante */}
              <div>
                <div className="flex items-center gap-2 mb-3">
                  <User className="w-5 h-5 text-[#3498DB]" />
                  <h3 className="text-gray-900">Información del Votante</h3>
                </div>
                <div className="bg-gray-50 rounded-lg p-4 space-y-2">
                  <div className="flex justify-between">
                    <span className="text-gray-600">Número de Cédula:</span>
                    <span className="text-gray-900">{cedula}</span>
                  </div>
                  <div className="flex justify-between">
                    <span className="text-gray-600">Fecha y Hora:</span>
                    <span className="text-gray-900">{currentDate}</span>
                  </div>
                </div>
              </div>

              {/* Información del proceso */}
              <div>
                <div className="flex items-center gap-2 mb-3">
                  <Calendar className="w-5 h-5 text-[#3498DB]" />
                  <h3 className="text-gray-900">Proceso Electoral</h3>
                </div>
                <div className="bg-gray-50 rounded-lg p-4 space-y-2">
                  <div className="flex justify-between">
                    <span className="text-gray-600">Nombre del Proceso:</span>
                    <span className="text-gray-900">Consulta Popular 2025</span>
                  </div>
                  <div className="flex justify-between">
                    <span className="text-gray-600">Tipo de Votación:</span>
                    <span className="text-gray-900">Digital Segura</span>
                  </div>
                  <div className="flex justify-between">
                    <span className="text-gray-600">Jurisdicción:</span>
                    <span className="text-gray-900">Nacional - Ecuador</span>
                  </div>
                </div>
              </div>

              {/* Validación digital */}
              <div>
                <div className="flex items-center gap-2 mb-3">
                  <Hash className="w-5 h-5 text-[#3498DB]" />
                  <h3 className="text-gray-900">Validación Digital</h3>
                </div>
                <div className="bg-gradient-to-r from-green-50 to-blue-50 rounded-lg p-4 border-2 border-green-200">
                  <div className="flex items-start gap-3 mb-3">
                    <ShieldCheck className="w-6 h-6 text-green-600 flex-shrink-0 mt-1" />
                    <div className="flex-1">
                      <p className="text-green-800 mb-2">Firma Electrónica Validada con FirmaEc</p>
                      <p className="text-sm text-gray-600">Su voto ha sido cifrado y registrado en la cadena de bloques electoral.</p>
                    </div>
                  </div>
                  <div className="space-y-2 text-sm">
                    <div className="flex justify-between">
                      <span className="text-gray-600">Hash de Transacción:</span>
                      <span className="text-gray-900 font-mono text-xs">0x7a8f9c...</span>
                    </div>
                    <div className="flex justify-between">
                      <span className="text-gray-600">ID de Certificado:</span>
                      <span className="text-gray-900 font-mono text-xs">CERT-2025-{cedula.slice(0, 4)}</span>
                    </div>
                  </div>
                </div>
              </div>

              {/* Nota de confidencialidad */}
              <div className="bg-blue-50 border-l-4 border-[#3498DB] p-4 rounded">
                <div className="flex items-start gap-3">
                  <FileText className="w-5 h-5 text-[#3498DB] flex-shrink-0 mt-0.5" />
                  <div className="flex-1">
                    <p className="text-sm text-gray-700">
                      <strong>Nota de Confidencialidad:</strong> Este certificado valida únicamente su participación en el proceso electoral. 
                      Su elección individual permanece anónima y confidencial, garantizando el secreto del voto según la legislación ecuatoriana.
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </Card>

          {/* Acciones */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <Button
              variant="secondary"
              onClick={handleDownloadCertificate}
              fullWidth
              icon={<Download className="w-5 h-5" />}
            >
              Descargar Certificado PDF
            </Button>
            <Button
              variant="secondary"
              onClick={handleEmailCertificate}
              fullWidth
              icon={<Mail className="w-5 h-5" />}
            >
              Enviar por Correo Electrónico
            </Button>
          </div>

          {/* Footer */}
          <div className="mt-8 text-center text-sm text-gray-500">
            <p>Sistema VotaYa - Plataforma de Votación Digital Segura</p>
            <p className="mt-1">Desarrollado bajo los estándares de seguridad del Consejo Nacional Electoral del Ecuador</p>
          </div>
        </div>
      </div>
    );
  };

  return (
    <>
      {currentScreen === 'login' && renderLogin()}
      {currentScreen === 'voting' && renderVoting()}
      {currentScreen === 'certificate' && renderCertificate()}
    </>
  );
}