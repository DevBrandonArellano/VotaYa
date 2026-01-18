import React from 'react';

interface InputProps {
  label: string;
  type?: string;
  value: string;
  onChange: (value: string) => void;
  error?: string;
  placeholder?: string;
  required?: boolean;
}

export function Input({
  label,
  type = 'text',
  value,
  onChange,
  error,
  placeholder,
  required = false,
}: InputProps) {
  return (
    <div className="flex flex-col gap-2 w-full">
      <label className="text-gray-700">
        {label}
        {required && <span className="text-red-500 ml-1">*</span>}
      </label>
      <input
        type={type}
        value={value}
        onChange={(e) => onChange(e.target.value)}
        placeholder={placeholder}
        className={`px-4 py-3 border-2 rounded-lg transition-colors focus:outline-none focus:ring-2 focus:ring-[#3498DB] focus:border-transparent ${
          error ? 'border-red-500 bg-red-50' : 'border-gray-300'
        }`}
      />
      {error && (
        <span className="text-red-500 text-sm">{error}</span>
      )}
    </div>
  );
}
